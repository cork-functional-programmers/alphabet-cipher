(defproject alphabet-cipher "0.1.0-SNAPSHOT"
  :description "Alphabet Cipher"
  :url "http://example.com/FIXME"
  :license {:name "The MIT License (MIT)"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot alphabet-cipher.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
