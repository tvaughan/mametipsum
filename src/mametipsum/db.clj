(ns mametipsum.db)

(defstruct
    ^{:private true}
  line :string :length)
(defstruct
    ^{:private true}
  script :title :lines)

(def
  ^{:private true}
  scripts (ref nil))

(defn- remove-directories [list]
  (remove #(.isDirectory %) list))

(defn- get-scripts []
  (remove-directories (file-seq (clojure.java.io/file "db"))))

(defn- parse-script [filename]
  (with-open [fd (clojure.java.io/reader filename)]
    (let [strings (line-seq fd)]
      (map (fn [string length]
             (struct line string length))
           strings (map count strings)))))

(defn- add-script [title lines]
  (dosync
   (ref-set scripts (cons @scripts [(struct script title lines)]))))

(defn init []
  (doall
   (for [script (get-scripts)]
     (add-script (.getName script) (parse-script script)))))

(defn list-scripts []
  ;; TODO: Get rid of default nil value.
  (rest @scripts))
