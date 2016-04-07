An issue with the composistion of Reagent's components
======================================================

As far as I understand, Reagent components can be composed to form new custom components by wrapping them into functions returning vectors respecting Hiccup's syntax:

    (defn my-new-component
      [& args]
      [my-original-component (some-other-stuff-depending-on args)])

In this repository you can find some code that includes components developed by the React community in a project called `React Router`. The strange thing is that when some of these components are used to produce new components as described above, the resulting app throws errors or do not display correcty (i.e. it is rendered as an empty `<noscript>`).

## Building and usage ##

After checking out and cd-ing into the project folder, run

    lein cljsbuild auto main

Then point your browser to `file:///<project's-complete-path>/html/index.html`.

You should see a working (though uninteresting :-) ) app. Now modify the code in `src/router-issue/core.cljs` by switching between commented and uncommented `def`s of `router`, `route` or `index-route` components. Upon reloading the page, the browser will show the described behavior.

## So... ##

Is this behavior expected? Maybe the composition of components is possible only with React's native components?

## Possible problems ##

The external components have been imported into Clojurescript from a bundled js file of the latest version of this lib (made available by the authors at [https://npmcdn.com/react-router@2.0.1/umd/ReactRouter.js](https://npmcdn.com/react-router@2.0.1/umd/ReactRouter.js) as described in the [README](https://github.com/reactjs/react-router/tree/v2.0.1#installation)). Could the bundling be responsible for this behavior? Or maybe these components lack the implementation of some optional method that Reagent relies on?
