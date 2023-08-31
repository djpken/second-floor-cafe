import {TransitionProps} from "@mui/material/transitions";
import {Button, Dialog, DialogActions, DialogContent, Slide, Stack, useTheme} from "@mui/material";
import React from "react";
import {useForm} from "react-hook-form";
import {FieldValues} from "react-hook-form/dist/types/fields";
import {apiPostDish} from "../../../../../api";
import {useMutation} from "@tanstack/react-query";


const postDish = async (formData: FormData) => {
    const response = await apiPostDish(formData)
    return response.data.data

}

const Transition = React.forwardRef(function Transition(
    props: TransitionProps & {
        children: React.ReactElement<any, any>;
    },
    ref: React.Ref<unknown>,
) {
    return <Slide direction="up" ref={ref} {...props} />;
});

interface ImageFormProps {
    open: boolean
    keepMounted: boolean
    onClose: () => void
}

const DialogSlide = () => {
    const [open, setOpen] = React.useState(false);
    const handleClickOpen = () => {
        setOpen(true);
    };
    const handleClose = () => {
        setOpen(false);
    };
    return (
        <Stack justifyContent={'center'}>
            <Button color='secondary' onClick={handleClickOpen}>
                上傳圖片
            </Button>
            <ImageForm onClose={handleClose} open={open} keepMounted/>
        </Stack>
    );
}
const ImageForm = (props: ImageFormProps) => {
    const theme = useTheme()
    const {open, onClose, ...other} = props
    const {
        register,
        handleSubmit,
    } = useForm();
    const mutationPostDish = useMutation({
        mutationFn: (formData: FormData) => postDish(formData)
    });

    const onSubmit = (data: FieldValues) => {
        const fileList: FileList = data.inputImage
        let formData = new FormData()
        for (let i = 0; i < fileList.length; i++) {
            let file = fileList[i]
            formData.append("files", file, file.name + "." + i)
        }
        mutationPostDish.mutate(formData)
    };
    return (
        <Dialog
            component={'form'}
            onSubmit={handleSubmit(onSubmit)}
            open={open}
            TransitionComponent={Transition}
            sx={{'& .MuiDialog-paper': {width: theme.spacing(100), height: theme.spacing(100), minHeight: 300}}}
            {...other}
        >
            <DialogContent>
                <Button color={'secondary'}>
                    上傳
                    <input {...register("inputImage")} type="file" multiple hidden accept={"image/*"}/>
                </Button>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
            </DialogActions>
        </Dialog>

    )
}
export default DialogSlide
