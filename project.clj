(defproject ktoto-service "0.1.0-SNAPSHOT"
  :description "Ktoto Service: a REST API for quiz games"
  :license {:name "GNU General Public License v3.0"
            :url "https://www.gnu.org/licenses/gpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [http-kit "2.2.0"]
                 [org.clojure/java.jdbc "0.7.1"]
                 [environ "1.1.0"]
                 [metosin/compojure-api "1.1.11"]
                 [org.clojure/data.json "0.2.6"]
                 [com.ashafa/clutch "0.4.0"]
                 [org.clojure/tools.logging "0.3.1"]]
  :ring {:handler ktoto-service.handler/app}
  :main ktoto-service.main
  :uberjar-name "app.jar"
  :profiles {:uberjar {:aot [ktoto-service.main]}
            :dev {:dependencies [[cheshire "5.8.0"]
                                 [javax.servlet/servlet-api "2.5"]
                                 [org.clojars.runa/conjure "2.2.0"]
                                 [ring/ring-mock "0.3.1"]]
                  :plugins [[lein-ring "0.9.7"]]}})
