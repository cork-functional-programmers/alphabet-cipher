(ns alphabet-cipher.core
  (:gen-class))

(defn cipher [msg key direction]
  (->> msg
      (map #(direction (- (int %2) (int \a)) (- (int %1) (int \a))) (flatten (cycle key)))
      (map #(mod % 26))
      (map #(char (+ % (int \a))))
      (apply str)))

(defn encode [msg key]
  (cipher msg key +))

(defn decode [msg key]
  (cipher msg key -))
