(defproject sts "0.1.0-SNAPSHOT"
  :description "Sales Tax Service"
  :url "http://localhost:3000"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0-alpha5"]
                 [compojure "1.3.2"]
                 [ring/ring-core "1.3.2"]
                 [ring/ring-json "0.3.1"]
                 [ring/ring-defaults "0.1.4"]
                 [yesql "0.4.0"]
                 [ragtime/ragtime.sql.files "0.3.8"]
                 [mysql/mysql-connector-java "5.1.6"]]
  :plugins [[lein-ring "0.8.13"]
            [ragtime/ragtime.lein "0.3.8"]]
  :ring {:handler sts.handler/app}
  :ragtime {:migrations ragtime.sql.files/migrations
            :database "jdbc:mysql://localhost:3306/sales_tax?user=cljrest&password=cljrest"}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
