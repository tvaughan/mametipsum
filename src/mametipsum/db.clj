(ns mametipsum.db)

(defn- remove-directories [list]
  (remove #(.isDirectory %) list))

(defn- get-blobs []
  (remove-directories (file-seq (clojure.java.io/file "db"))))

(defstruct line :string :length)

(defn- parse-blob [strings]
  (doall
   (map (fn [string length]
          (struct line string length))
        strings (map count strings))))

(defn- add-blob [filename]
  (with-open [fd (clojure.java.io/reader filename)]
    (parse-blob (line-seq fd))))

(def *scripts* (ref {}))

(defn get-scripts []
  *scripts*)

(defn list-titles [scripts]
  (keys @scripts))

(defn- print-titles [scripts]
  (doseq [title (list-titles scripts)]
    (println title)))

(defn list-values [scripts]
  (vals @scripts))

(defn- print-values [scripts]
  (doseq [value (list-values scripts)]
    (println value)))

(defn create-script [scripts title data]
  (dosync (alter scripts assoc title data)))

(defn- iter-script [script nwords]
  (loop [i 0 current (first script) remainder (rest script) lines []]
    (if (< i nwords)
      (let [total (+ i (current :length))]
        (recur total (first remainder) (rest remainder) (concat lines [(current :string)])))
      lines)))

(defn read-script [scripts title nwords]
  (iter-script (scripts title) (Integer/parseInt nwords)))

(defn update-script [scripts title data]
  ;; TODO:
  )

(defn delete-script [scripts title]
  (dosync (alter scripts dissoc title)))

(defn init []
  (let [scripts (get-scripts)]
    (doseq [blob (get-blobs)]
      (create-script scripts (.getName blob) (add-blob blob)))))
