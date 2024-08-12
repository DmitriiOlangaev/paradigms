(load-file "proto.clj")

(def _arguments (field :arguments)); Operands in operations

(def _value (field :value)); Const and Variable

(def _sign (field :sign))

(def _apply (field :apply))

(def _df (field :df))

(def evaluate (method :evaluate));

(def toString (method :toString))

(def diff (method :diff))

(declare ZERO ONE Add Subtract Multiply Divide Negate)

(def ConstantPrototype
  {
    :diff (fn [this diffName] ZERO)
    :evaluate (fn [this varsValues] (_value this))
    :toString (fn [this] (str (_value this)))
  }
)

(def VariablePrototype
  {
   :diff (fn [this diffName]
           (if (= (_value this) diffName)
             ONE
             ZERO
             )
           )
   :evaluate (fn [this varsValues] (get varsValues (_value this)))
   :toString (fn [this] (_value this))
   }
)

(def Constant
  (constructor
    (fn [this value] (assoc this :value value))
    ConstantPrototype
  )
)

(def Variable
  (constructor
    (fn [this value] (assoc this :value value))
    VariablePrototype
  )
)

(def ZERO (Constant 0))
(def ONE  (Constant 1))
(def TWO  (Constant 2))

(def OperationsPrototype
  {
    :diff     (fn [this diffName] ((_df this) (_arguments this) (mapv #(diff % diffName) (_arguments this))))
    :evaluate (fn [this varsValues] (apply (_apply this) (mapv #(evaluate % varsValues) (_arguments this))))
    :toString (fn [this] (format "(%s %s)" (_sign this) (clojure.string/join " " (mapv #(toString %) (_arguments this)))))
  }
)

(defn createOperation
  [f df sign] (constructor
    (fn [this & arguments] (assoc this :arguments arguments))
    (assoc OperationsPrototype :apply f :df df :sign sign)
  )
)

(def Add (createOperation
   +
   (fn [args dArgs] (apply Add dArgs))
   "+"
 )
)

(def Subtract (createOperation
    -
    (fn [args dArgs] (apply Subtract dArgs))
    "-"
  )
)

(def Multiply (createOperation
    *
    (fn [args dArgs] (
        let [multiplied_Args (apply Multiply args)]
        (apply Add
          (map-indexed
            (fn [idx item] (Multiply (nth dArgs idx) (Divide multiplied_Args item)))
            args
          )
        )
      )
    )
    "*"
))

(defn Square [x] (Multiply x x))

(def Divide (createOperation
    my_divide
    (fn [args dArgs] (
       if (== (count args) 1)
          (Divide (Negate (first dArgs)) (Square (first args)))
          (let [denominator (apply Multiply (rest args))]
            (Divide
              (Subtract
                (Multiply (first dArgs) denominator)
                (Multiply (first args) ((_df denominator) (rest args) (rest dArgs)))
              )
              (Square denominator)
            )
          )
      )
    )
    "/"
  )
)

(def Negate (createOperation
    #(- %)
    (fn [args dArgs] (Negate (first dArgs)))
    "negate"
  )
)

(def Mean (createOperation
    (fn [& args] (my_divide (apply + args) (count args)))
    (fn [args dArgs] (Divide
      (apply Add dArgs)
      (Constant (count args))
     )
    )
    "mean"
  )
)

(def Varn (createOperation
    (fn [& args] (
      let [len (count args)]
      (-
        (my_divide
         (apply + (mapv square args))
         len
        )
        (my_divide
          (square (apply + args))
          (square len)
        )
      )
     )
    )
    (fn [args dArgs](
       let [len (Constant (count args))]
       (Subtract
         (Divide
           (Multiply TWO (apply Add (mapv #(Multiply %1 %2) args dArgs)))
           len
         )
         (Divide
           (let [sum (apply Add args)]
             (Multiply TWO sum ((_df sum) args dArgs))
           )
           (Square len)
         )
       )
      )
    )
    "varn"
  )
)

(def ObjectOperations
  {
   '+ Add,
   '- Subtract,
   '* Multiply,
   '/ Divide,
   'negate Negate
   'mean Mean
   'varn Varn
  }
)

(def parseObject (createParser ObjectOperations Constant Variable))


