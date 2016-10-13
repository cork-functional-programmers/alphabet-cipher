# Alphabet Cipher - In Clojure, by Tim Cooke (Functional Kats Belfast)

Making fun use of the Arrow Macro (a.k.a Thread Macro).

## Solution 1

There's a lot of duplication here. The only variant is the map function of either + or -.

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

## Solution 2

Duplication removed by replacing the single variant function with a function argument.

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
