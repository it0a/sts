(ns sts.query
  (:require [yesql.core :refer [defquery]]))

(def db-spec
  {:classname "com.mysql.jdbc.Driver"
   :subprotocol "mysql"
   :subname "//localhost:3306/sts"
   :user "root"
   :password "sriq@"})

(defquery invoices "query/invoice/get-invoices.sql")

(defquery invoice "query/invoice/get-invoice.sql")

(defquery insert-invoice<! "query/invoice/add-invoice.sql")

(defquery total-invoiced "query/invoice/total-invoiced.sql")

(defn get-invoices []
  (invoices db-spec))

(defn get-invoice [id]
  (invoice db-spec id))

(defn add-invoice [number amount]
  (insert-invoice<! db-spec number amount))

(defn sum-invoices []
  (total-invoiced db-spec))
