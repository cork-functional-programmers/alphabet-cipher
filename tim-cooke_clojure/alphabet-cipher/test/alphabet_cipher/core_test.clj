(ns alphabet-cipher.core-test
  (:require [clojure.test :refer :all]
            [alphabet-cipher.core :refer :all]))

(deftest a-test
  (testing "Default example encode"
    (let [secret "scones"
          messg "meetmebythetree"]
      (is (= "egsgqwtahuiljgs" (encode messg secret)))))

  (testing "Default example decode"
    (let [secret "scones"
          messg "egsgqwtahuiljgs"]
      (is (= "meetmebythetree" (decode messg secret))))))
