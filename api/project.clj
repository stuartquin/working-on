(defproject clojure-rest "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring/ring-json "0.1.2"]
                 [c3p0/c3p0 "0.9.1.2"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [com.h2database/h2 "1.3.168"]
                 [cheshire "5.1.1"]
                 [compojure "1.1.5"]
                 [clj-time "0.6.0"]
                 [com.novemberain/monger "1.5.0"]]
  :plugins [[lein-ring "0.8.5"]]
  :ring {:handler clojure-rest.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]]}})
