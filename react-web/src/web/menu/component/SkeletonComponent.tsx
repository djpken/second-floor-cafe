import Grid2 from "@mui/material/Unstable_Grid2"
import Skeleton from "@mui/material/Skeleton";
import React from "react";

const SkeletonComponent = ({animation}: { animation: 'pulse' | 'wave' | false }) => {
    return (
        <Grid2 container spacing={1}>
            {[...Array(100)].map((_, i) => (
                <Grid2 xs={2.4} key={i}>
                    <Skeleton
                        sx={{borderRadius: "15px"}}
                        variant="rounded"
                        height={"36vh"}
                        animation={animation}>
                    </Skeleton>
                </Grid2>
            ))}
        </Grid2>
    );
}
export default SkeletonComponent;
