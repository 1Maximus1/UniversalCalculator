import React, { FC, useState, useEffect } from "react";
import Button from "../../ui/Button/Button";
import CM_Modal from "../../ui/CM_Modal/CM_Modal";
import Matrix from "../Matrix/Matrix";

type Size = {
  rows: number;
  columns: number;
};

interface IFullMatrix {
  setMatrix: any;
}

const FullMatrix: FC<IFullMatrix> = ({ setMatrix }) => {
  const [size, setSize] = useState<Size>();
  const [condition, setCondition] = useState<boolean>(false);

  useEffect(() => {
    setCondition(prevValue => !prevValue);
  }, [size]);

  const reset = () => {
    setSize(undefined);
    setMatrix(undefined);
  };

  return (
    <div>
      {!size ? (
        <Button onClick={() => setCondition(true)}>add matrix</Button>
      ) : (
        <>
          <Matrix
            columns={size.columns}
            rows={size.rows}
            getMatrix={setMatrix}
          />
          <Button style={{ marginTop: "10px" }} onClick={() => reset()}>
            reset
          </Button>
        </>
      )}
      <CM_Modal visible={condition} getData={setSize} />
    </div>
  );
};

export default FullMatrix;
