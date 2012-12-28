(ns mametipsum.test.core
  (:require [mametipsum.db :as db]
            [mametipsum.scripts :as scripts]
            [clojure.test :refer [deftest is use-fixtures]]))

(defn db-fixture [f]
  (db/init)
  (f))

(use-fixtures :once db-fixture)

(defn- in?
  [seq elm]
  (some #(= elm %) seq))

(deftest db-testsuite
  (let [handle (scripts/get-handle)]
    (let [titles (scripts/list-titles handle)]
      (is (true? (in? titles "Glengarry Glen Ross")))
      (is (true? (in? titles "The Verdict"))))))
