(ns musings.core)

(defn ^{:doc "Pancake sort, insert the spatula at a point and flip all numbers above
[8 7 6 5 4 3] => [6 7 8 5 4 3]
      ^
(pancake vector- 3)"}
  pancake [vec- n & a]
  (if (> n (count vec-))
    vec-
    (let [v1     (subvec vec- 0 n)
          v2     (subvec vec- n)
          rev-v1 (vec (reverse v1))
          out    (into rev-v1 v2)]
      #_(if (seq a)
        (println vec- "=>" out a)
        (println vec- "=>" out))
      out)))

(defn ^{:doc "Calculates how long the ascending or descending streak is"}
  streak-length [vec-]
  (loop [i    2
         asc  1
         desc 1
         n    (count vec-)]
    ;; (prn (take i vec-))
    (cond
     (> i n) [asc desc n]
     (apply <= (take i vec-)) (recur (inc i) (inc asc) desc n)
     (apply >= (take i vec-)) (recur (inc i) asc (inc desc) n)
     :else [asc desc n])))

(defn ^{:doc "Checks the vector and specified where to pancake"}
  where-cake [vec-]
  (let [[asc desc] (streak-length vec-)]
    (cond
      ;(< (last vec-) (first vec-)) (pancake vec- (count vec-))
      (>= asc desc)                (pancake (pancake vec- asc "asc" asc desc)
                                            (inc asc) "asc" asc desc)
      (< asc desc)                 (pancake vec- desc "desc" asc desc)
      )))

(defn ^{:doc "Returns the index of the highest out of order slice"}
  highest-oo [vec-]
  (let [i       #(map-indexed vector %)
        matches (fn [v n] (filter #(= n (second %)) v))]
    (loop [v vec-
           j 0]
       (let [indexed (i v)
             biggest (apply max v)
             size (count v)
             match (last (matches indexed biggest))]
         ;; (println j size match)
         (if (or (= j (count vec-)) (= (first match) (dec size)))
           (recur (pop v) (inc j))
           (into [size] match))))))

(defn ^{:doc "Bottom up pancake method"}
  bot-cake [vec-]
  (let [[ideal-index
         current-index
         biggest-oo] (highest-oo vec-)]
    (if (= current-index 0)
      (pancake vec- ideal-index)
      (pancake vec- (inc current-index)))))

(defn ^{:doc "Solves the sorting problem with pancake"}
  solve-cake [vec- func]
  (loop [v vec-
         i 0]
    ;; (println v)
    (if (or (> i 10000) (apply <= v))
      [v i]
      (recur (func v) (inc i)))))
