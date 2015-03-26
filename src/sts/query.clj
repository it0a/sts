(ns sts.query
  (:require [yesql.core :refer [defquery]]))

(def db-spec
  {:classname "com.mysql.jdbc.Driver"
   :subprotocol "mysql"
   :subname "//localhost:3306/sales_tax"
   :user "root"
   :password "sriq@"})

(defquery tax "query/tax/get-tax.sql")
(defquery tax-by-zip "query/tax/tax-by-zip.sql")
(defquery tax-by-city "query/tax/tax-by-city.sql")

(defn get-tax [id]
  (tax db-spec id))

(defn get-tax-by-zip [zip]
  (tax-by-zip db-spec zip))

(defn get-tax-by-city [city]
  (tax-by-city db-spec city))

