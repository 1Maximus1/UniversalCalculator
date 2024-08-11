import React, { FC, useEffect, useState } from "react";
import Modal from "../Modal/Modal";
import ICM_Modal from "./ICM_Modal";
import Input from "./components/Input/Input";
import Tile from "./components/Tile/Tile";
import styles from "./CM_Modal.module.scss";
const CM_Modal: FC<ICM_Modal> = ({ visible, getData }) => {
  const [row, setRow] = useState(2);
  const [column, setColumn] = useState(2);
  const [matrix, setMatrix] = useState(
    Array.from({ length: 5 }, (_, rowIndex) =>
      Array.from({ length: 5 }, (_, colIndex) => ({
        row: rowIndex,
        column: colIndex,
        checked: rowIndex < row && colIndex < column,
      }))
    )
  );
  useEffect(() => {
    console.log("remder");
    setMatrix(matrix =>
      matrix.map((rows, indexR) =>
        rows.map((_, indexC) => ({
          row,
          column,
          checked: indexR < row && indexC < column,
        }))
      )
    );
  }, [row, column]);

  const sendData = (rows: number, columns: number) => {
    if (!rows || !columns) return;
    getData({ rows, columns });
  };

  return (
    <Modal visible={visible}>
      <div className={styles.modal}>
        <div className={styles.wrapper}>
          <header style={{ marginBottom: "7px", textAlign: "center" }}>
            Matrices
          </header>
          <div className={styles.inputs}>
            <Input value={row} onChange={e => setRow(+e.target.value)} />
            X
            <Input value={column} onChange={e => setColumn(+e.target.value)} />
          </div>
          <div className={styles.matrix}>
            {matrix.map((rows, indexR) =>
              rows.map((_, indexC) => (
                <div
                  onClick={() => {
                    setColumn(indexC + 1);
                    setRow(indexR + 1);
                  }}
                >
                  <Tile
                    key={`${indexR}-${indexC}`}
                    checked={indexR < row && indexC < column}
                  />
                </div>
              ))
            )}
          </div>
          <button
            style={{
              backgroundColor: "rgb(8, 123, 195)",
              border: "none",
              borderRadius: "10px",
              width: "70px",
              height: "25px",
              margin: "auto",
              cursor: "pointer",
              color: "white",
              fontSize: "20px",
            }}
            onClick={() => sendData(row, column)}
          >
            Insert
          </button>
        </div>
      </div>
    </Modal>
  );
};

export default CM_Modal;
