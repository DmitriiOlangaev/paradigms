(def constant constantly)

(defn variable [varName]
  (fn [varsValues] (get varsValues varName))
)

(defn createOperation [f]
  (fn [& operands]
    (fn [varsValues]
      (apply f (mapv #(% varsValues) operands))
    )
  )
)
(def add (createOperation +))

(def subtract (createOperation -))

(def multiply (createOperation *))

(defn my_divide
  ([x] (/ 1.0 (double x)))
  ([x y & operands]
   (/ (double x) (double (* y (reduce * operands))))
  )
)

(def divide (createOperation my_divide))

(defn negate [x] (subtract x))

(def mean (createOperation
    (fn [& operands]
      (my_divide (apply + operands) (count operands))
    )
))

(defn square [x] (* x x))

(def varn (createOperation
  (fn [& operands]
    (-
      (/
        (apply + (mapv square operands))
        (count operands)
      )
      (/
        (square (apply + operands))
        (square (count operands))
      )
    )
  )
))

(def Operations {
   '+ add,
   '- subtract,
   '* multiply,
   '/ divide,
   'negate negate
   'mean mean
   'varn varn
})

(defn createParser [OperationsMap Const Var] (fn[expr] (
 letfn [
        (parse [token]
          (cond (list? token) (apply (get OperationsMap (first token)) (mapv parse (rest token)))
                (number? token) (Const token)
                :else (Var (name token)))
          )
        ]
 (parse (read-string expr))
)))

(def parseFunction (createParser Operations constant variable))

(load-file "object.clj")

