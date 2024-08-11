function Paint(value: number | number[][], columns: number) {
  const columnTemplate = [...Array(columns)].map(() => "70px").join(" ");
  if (typeof value === "number") {
    return (
      <div style={{ fontSize: "20px", color: "rgb(8, 123, 195)" }}>{value}</div>
    );
  } else if (Array.isArray(value)) {
    return (
      <div
        style={{
          display: "grid",
          gridGap: "5px",
          gridTemplateColumns: columnTemplate,
        }}
      >
        {value[0].map((_, colIndex) => (
          <div
            key={colIndex}
            style={{ display: "grid", gridGap: "5px", textAlign: "center" }}
          >
            {value.map((row, rowIndex) => (
              <div
                key={`${colIndex}-${rowIndex}`}
                style={{
                  padding: "10px",
                  border: "2px solid gray",
                  borderRadius: "15px",
                }}
              >
                {row[colIndex]}
              </div>
            ))}
          </div>
        ))}
      </div>
    );
  }
}

export default Paint;
