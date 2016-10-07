(ns alphabet-cipher.core
  (:gen-class))

(defn encode [msg key]
  (->> msg
        (map #(+ (- (int %2) (int \a)) (- (int %1) (int \a))) (flatten (cycle key)))
        (map #(mod % 26))
        (map #(char (+ % (int \a))))
        (apply str)))

(defn decode [msg key]
  (->> msg
      (map #(- (- (int %2) (int \a)) (- (int %1) (int \a))) (flatten (cycle key)))
      (map #(mod % 26))
      (map #(char (+ % (int \a))))
      (apply str)))
