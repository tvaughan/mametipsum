(ns mametipsum.test.core
  (:require
   [mametipsum.db :as db])
  (:use
   [clojure.test]))

(deftest test-init-db
  (let [script (db/init)]
    (is (= (count (first script)) 1331))))
