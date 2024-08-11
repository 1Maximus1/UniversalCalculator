import { useEffect, useState } from "react";
import Tile from "../../ui/Tile/Tile";
import styles from "./Matrix.module.scss";

interface IMatrix {
  value: number | undefined;
  id: string;
}

const Matrix = ({
  rows,
  columns,
  getMatrix,
}: {
  rows: number;
  columns: number;
  getMatrix: any;
}) => {
  const [matrix, setMatrix] = useState<IMatrix[][]>(
    Array.from({ length: rows }, (_, rowIndex) =>
      Array.from({ length: columns }, (_, colIndex) => ({
        value: 0,
        id: `${rowIndex}-${colIndex}`,
      }))
    )
  );
  const handleTileChange = (
    newValue: string,
    rowIndex: number,
    colIndex: number
  ) => {
    const newMatrix = matrix.map((row, rIndex) =>
      row.map((tile, cIndex) =>
        rIndex === rowIndex && cIndex === colIndex
          ? {
              ...tile,
              value: isNaN(parseInt(newValue)) ? 0 : parseInt(newValue),
            }
          : tile
      )
    );
    setMatrix(newMatrix);
    getMatrix(newMatrix.map(rows => rows.map(column => column.value)));
  };

  useEffect(() => {
    getMatrix(matrix.map(rows => rows.map(column => column.value)));
  }, []);

  const columnTemplate = [...Array(columns)].map(() => "50px").join(" ");

  return (
    <div
      className={styles.matrix}
      style={{
        gridTemplateColumns: columnTemplate,
      }}
    >
      {matrix[0].map((_, colIndex) => (
        <div key={colIndex} style={{ display: "grid", gridGap: "5px" }}>
          {matrix.map((row, rowIndex) => (
            <Tile
              key={row[colIndex].id}
              value={row[colIndex].value}
              rowIndex={rowIndex}
              colIndex={colIndex}
              getValue={handleTileChange}
            />
          ))}
        </div>
      ))}
    </div>
  );
};

export default Matrix;
