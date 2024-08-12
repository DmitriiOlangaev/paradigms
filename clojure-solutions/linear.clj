(defn checkVector
  ([v] (and (vector? v) (or (empty? v) (every? number? v))))
  ([v size] (and (checkVector v) (= (count v) size)))
)

(defn checkMatrix
  ([m] (and (vector? m) (or (and (= (count m) 1) (vector? (first m)) (empty? (first m)))
           (let [size (count (first m))] (every? #(checkVector % size) m)))))
  ([m size1] (and (checkMatrix m) (= (count m) size1)))
  ([m size1 size2] (and (checkMatrix m size1) (every? #(checkVector % size2) m)))
)

(defn createApplicable [f, pre] (fn [& arg] {:pre [(apply pre arg)]}
                                  (apply mapv f arg)))

(defn transpose [m] (apply mapv vector m))

(defn createVectorByElementOperation [f] (createApplicable f (fn [fst & arg] (and (checkVector fst) (every? #(checkVector % (count fst)) arg)))))

(def v+ (createVectorByElementOperation +))

(def v* (createVectorByElementOperation *))

(def v- (createVectorByElementOperation -))

(def vd (createVectorByElementOperation /))

(defn v*s [v & s] (mapv #(* % (apply * s)) v))

(defn scalar [& arg] (apply + (apply v* arg)))

(defn vect [& arg] (
                     letfn [
                            (cross_product [a b]
                              (letfn [(det [i j] (- (* (nth a i) (nth b j)) (* (nth a j) (nth b i))))]
                                [(det 1 2)  (det 2 0)  (det 0 1)])
                            )
                            ] (reduce cross_product arg)
))

(defn createMatrixByElementOperation [f] (createApplicable f (fn [fst & arg] (and (checkMatrix fst) (every? #(checkMatrix (count fst) (count (first fst))) arg)))))

(def m+ (createMatrixByElementOperation v+))

;(def m+ (createApplicable v+ (constantly true)))

;(def m- (createMatrixByElementOperation v-))

(def m- (createApplicable v- (constantly true)))

;(def m* (createMatrixByElementOperation v*))

(def m* (createApplicable v* (constantly true)))

;(def md (createMatrixByElementOperation vd))

(def md (createApplicable vd (constantly true)))

(defn m*s [m & s] (mapv #(v*s % (apply * s)) m))

(defn m*v [m & v] (let [val (apply v* v)]
                  (mapv #(scalar % val) m)))

(defn m*m [& arg] (reduce (fn [a b] (mapv #(m*v (transpose b) %) a)) arg))

;(println (checkVector [1 1] (count [1 2 3])))
;(println (m*m [[1]] [[2]]))
;(println (checkMatrix [[1 2 ] [2 3]] 1 2))
(println (m+ [[0]] [[1]]))
