import React, { useCallback, useMemo, useState } from "react";
import MaterialReactTable, {
  MaterialReactTableProps,
  MRT_ColumnDef,
  MRT_PaginationState,
  MRT_Row,
} from "material-react-table";
import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  IconButton,
  Stack,
  TextField,
  Tooltip,
} from "@mui/material";
import { apiGetSysUser } from "../../api";
import { useQuery } from "@tanstack/react-query";
import RefreshIcon from "@mui/icons-material/Refresh";

export default function Authority() {
  return <Table />;
}
type User = {
  id: number;
  userName: string;
  createTime: string;
  updateTime: string;
  department: string;
};
type UserApiResponse = {
  records: User[];
  total: number;
  meta: {
    totalRowCount: number;
  };
};

interface Row {
  id: number;
  userNumber: string;
  createTime: string;
  updateTime: string;
  department: string;
}

function Table() {
  const [createModalOpen, setCreateModalOpen] = useState(false);
  const [validationErrors, setValidationErrors] = useState<{
    [cellId: string]: string;
  }>({});
  const [pagination, setPagination] = useState<MRT_PaginationState>({
    pageIndex: 0,
    pageSize: 10,
  });
  const handleSaveRowEdits: MaterialReactTableProps<User>["onEditingRowSave"] =
    async ({ exitEditingMode, row, values }) => {};
  const handleCancelRowEdits = () => {
    setValidationErrors({});
  };
  const handleDeleteRow = useCallback((row: MRT_Row<User>) => {}, []);
  const handleCreateNewRow = (values: User) => {};
  const queryFn = async () => {
    const response = await apiGetSysUser(
      pagination.pageIndex,
      pagination.pageSize
    );
    return response.data;
  };
  const { data, isError, isFetching, isLoading, error, refetch } =
    useQuery<UserApiResponse>({
      queryKey: ["sysUser", pagination.pageIndex, pagination.pageSize],
      queryFn: queryFn,
      keepPreviousData: true,
    });
  const columnsConfig = useMemo<MRT_ColumnDef<User>[]>(
    () => [
      {
        accessorKey: "id",
        header: "ID",
      },
      {
        accessorKey: "userName",
        header: "工號",
      },
      {
        accessorFn: (row) => new Date(row.createTime),
        id: "createTime",
        header: "建立時間",
      },
      {
        accessorFn: (row) => new Date(row.updateTime),
        id: "updateTime",
        header: "更新時間",
      },
      {
        accessorKey: "department",
        header: "部門",
      },
    ],
    []
  );
  return (
    <>
      <MaterialReactTable
        columns={columnsConfig}
        data={data?.records ?? []}
        initialState={{ showColumnFilters: true }}
        manualPagination
        muiToolbarAlertBannerProps={
          isError
            ? { color: "error", children: "Error loading Date" }
            : undefined
        }
        onPaginationChange={setPagination}
        renderTopToolbarCustomActions={() => (
          <Stack spacing={1} direction={"row"}>
            <Tooltip arrow title={"Refresh Data"}>
              <IconButton onClick={() => refetch()}>
                <RefreshIcon />
              </IconButton>
            </Tooltip>
            <Button
              onClick={() => setCreateModalOpen(true)}
              variant="contained"
            >
              Create New Account
            </Button>
          </Stack>
        )}
        rowCount={data?.total ?? 0}
        state={{
          pagination,
          isLoading,
          showAlertBanner: isError,
          showProgressBars: isFetching,
        }}
      />
      <Create
        columns={columnsConfig}
        open={createModalOpen}
        onClose={() => setCreateModalOpen(false)}
        onSubmit={handleCreateNewRow}
      />
    </>
  );
}

const Create = ({
  open,
  columns,
  onClose,
  onSubmit,
}: {
  open: boolean;
  columns: MRT_ColumnDef<User>[];
  onClose: () => void;
  onSubmit: (values: User) => void;
}) => {
  const [values, setValues] = useState<any>(() =>
    columns.reduce((acc, column) => {
      acc[column.accessorKey ?? ""] = "";
      return acc;
    }, {} as any)
  );
  const handleSubmit = () => {
    onSubmit(values);
    onClose();
  };
  return (
    <Dialog open={open}>
      <DialogTitle textAlign="center">Create New Account</DialogTitle>
      <DialogContent>
        <form onSubmit={(e) => e.preventDefault()}>
          <Stack
            sx={{
              width: "100%",
              minWidth: { xs: "300px", sm: "360px", md: "400px" },
              gap: "1.5rem",
            }}
          >
            {columns.map((column) => (
              <TextField
                key={column.accessorKey}
                label={column.header}
                name={column.accessorKey}
                onChange={(e) =>
                  setValues({ ...values, [e.target.name]: e.target.value })
                }
              />
            ))}
          </Stack>
        </form>
      </DialogContent>
      <DialogActions sx={{ p: "1.25rem" }}>
        <Button onClick={onClose}>Cancel</Button>
        <Button color="secondary" onClick={handleSubmit} variant="contained">
          Create New Account
        </Button>
      </DialogActions>
    </Dialog>
  );
};
