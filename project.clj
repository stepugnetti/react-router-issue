(defproject router-issue "0.1.0"
  :description "A repository to reproduce a strange behavior of Reagent's custom components."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [reagent "0.5.1"]]
  :plugins [[lein-cljsbuild "1.1.1"]]
  :main ^:skip-aot router-issue.core
  :target-path "target/%s"
  :cljsbuild {:builds {:main
                       {:source-paths ["src"]
                        :compiler {:output-dir "target"
                                   :output-to "target/main.js"
                                   :main "router-issue.core"
                                   :externs ["foreign-libs/react-router/exts.js"]
                                   :foreign-libs [{:file "foreign-libs/react-router/src/ReactRouter.js"
                                                   :provides ["cljsjs.react-router"]}]
                                   :optimizations :none
                                   :asset-path "js"}}}}
  :profiles {:uberjar {:aot :all}})
