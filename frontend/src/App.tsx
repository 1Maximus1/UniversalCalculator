import "./App.css";
import { Route, Routes } from "react-router-dom";
import { Suspense, lazy } from "react";

const Outlet = lazy(() => import("./components/Layout/Layout"));
const Sinlge = lazy(() => import("./pages/SingleMatrix"));
const Multi = lazy(() => import("./pages/DoubleMatrix"));

function App() {
  return (
    <div className='App'>
      <Suspense fallback={<div>loading....</div>}>
        <Routes>
          <Route path='/' element={<Outlet />}>
            <Route index element={<Sinlge />} />
            <Route path='multiple' element={<Multi />} />
          </Route>
        </Routes>
      </Suspense>
    </div>
  );
}

export default App;
