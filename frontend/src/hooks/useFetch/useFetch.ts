import { useState } from "react";
export default function useFetch<T>() {
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<Error>();
  const [value, setValue] = useState<T>();

  function fetchdata(url: string, method: string, data?: string) {
    if (!url || !method) return;
    setLoading(true);
    fetch(`${process.env.REACT_APP_HOST}${url}`, {
      method,
      body: data,
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then(res => res.json() as T)
      .then(res => setValue(res))
      .catch(error => setError(error))
      .finally(() => setLoading(false));
  }
  return { fetchdata, value, loading, error };
}
