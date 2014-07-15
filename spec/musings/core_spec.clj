(ns musings.core-spec
  (:require [speclj.core :refer :all]
            [musings.core :refer :all]))

(describe "core modules"
  (it "FIXedME, I fail."
      (should= 1 1))

  (it "can pancake"
      (should= [3 2 4 7 9] (pancake [4 2 3 7 9] 3)))

  (it "can streak length"
      (should= [1 2 5] (streak-length [4 2 3 7 9]))
      (should= [2 1 5] (streak-length [2 4 3 7 9]))
      (should= [5 1 5] (streak-length [2 3 4 7 9])))

  (it "Can see the highest oo"
      (should= [4 0 4] (highest-oo [4 1 2 3]))
      (should= [3 0 3] (highest-oo [3 1 2 4])))

  (it "can where-cake"
      (should= [2 4 3 7 9] (where-cake [4 2 3 7 9]))
      (should= [2 3 4 7 9] (where-cake [4 3 2 7 9]))
      (should= [4 2 3 7 9] (where-cake [2 3 7 9 4])))



  (it "Can solve with pancake"
      (should= [[2 3 4 7 9] 3] (solve-cake [4 2 3 7 9] where-cake))
      (should= [[2 3 4 7 9] 2] (solve-cake [4 2 3 7 9] bot-cake))
      (should= [[2 3 4 7 9 11] 19] (solve-cake [4 2 3 7 11 9] where-cake))
      (should= [[2 3 4 7 9 11] 6] (solve-cake [4 2 3 7 11 9] bot-cake))
      ;; (should= [(vec (range 15)) 4256] (solve-cake [3 4 10 14 8 9 11 13 1 2 7 6 5 0 12] where-cake))
      (should= [(vec (range 15)) 16] (solve-cake [3 4 10 14 8 9 11 13 1 2 7 6 5 0 12] bot-cake))
      ))
