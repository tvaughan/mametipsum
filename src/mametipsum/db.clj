(ns mametipsum.db)

(defn- remove-directories [list]
  (remove #(.isDirectory %) list))

(defn- get-blobs []
  (remove-directories (file-seq (clojure.java.io/file "db"))))

(defstruct line :string :length)

(defn- parse-blob [filename]
  (with-open [fd (clojure.java.io/reader filename)]
    (let [strings (line-seq fd)]
      (map (fn [string length]
             (struct line string length))
           strings (map count strings)))))

(def *scripts* (ref {}))

(defn get-scripts []
  *scripts*)

(defn- add-script [scripts title lines]
  (dosync (alter scripts assoc title lines)))

(defn get-script-titles [scripts]
  (keys @scripts))

(defn- print-script-titles [scripts]
  (doseq [title (get-script-titles scripts)]
    (println title)))

(defn init []
  (let [scripts (get-scripts)]
    (doseq [blob (get-blobs)]
      (add-script scripts (.getName blob) (parse-blob blob)))
    (print-script-titles scripts)))
