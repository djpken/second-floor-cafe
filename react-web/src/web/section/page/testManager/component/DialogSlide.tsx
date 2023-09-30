import {TransitionProps} from "@mui/material/transitions";
import {
    Box,
    Button,
    Dialog,
    DialogActions,
    DialogTitle,
    DialogContent,
    Slide,
    Stack,
    SxProps,
    Theme,
    IconButton,
    useTheme, InputLabel, Select, MenuItem, FormControl
} from "@mui/material";
import React, {useState, useEffect} from "react";
import {useForm} from "react-hook-form";
import {apiPostMenuDishPhoto, apiPostMenuDishText} from "../../../../../api";
import {useMutation, UseMutationOptions} from "@tanstack/react-query";
import Grid2 from "@mui/material/Unstable_Grid2";
import CancelOutlinedIcon from '@mui/icons-material/CancelOutlined';
import CancelRoundedIcon from '@mui/icons-material/CancelRounded';
import {MenuDishText} from "../../../../../entity";


const postMenuDishPhoto: UseMutationOptions<any, any, any, any> = {
    mutationFn: async (formData: FormData) => {
        const response = await apiPostMenuDishPhoto(formData)
        return response.data.data
    }
}
const postMenuDishText: UseMutationOptions<any, any, any, any> = {
    mutationFn: async (menuDishTextArray: MenuDishText[]) => {
        const response = await apiPostMenuDishText(menuDishTextArray)
        return response.data.data
    },
}


const Transition = React.forwardRef(function Transition(
    props: TransitionProps & {
        children: React.ReactElement<any, any>;
    },
    ref: React.Ref<unknown>,
) {
    return <Slide direction="up" ref={ref} {...props} />;
});

interface DialogSlidProps {
    searchQuery: string
    defaultValue: string
}

interface ImageFormProps extends DialogSlidProps {
    open: boolean
    keepMounted: boolean
    onClose: () => void
    searchQuery: string
    defaultValue: string
}

interface ImageFile {
    file: File
    imageSrc: string
}

const DialogSlide = ({searchQuery, defaultValue}: DialogSlidProps) => {
    const [open, setOpen] = React.useState(false);
    const handleClickOpen = () => {
        setOpen(true);
    };
    const handleClose = () => {
        setOpen(false);
    };
    return (
        <Stack>
            <Button sx={{height: '100%'}} color='secondary' onClick={handleClickOpen}>
                上傳圖片
            </Button>
            <ImageForm searchQuery={searchQuery} defaultValue={defaultValue} onClose={handleClose} open={open}
                       keepMounted/>
        </Stack>
    );
}
const ImageForm = (props: ImageFormProps) => {
    const mutationPostMenuDishPhoto = useMutation(postMenuDishPhoto);
    const mutationPostMenuDishText = useMutation<MenuDishText[], any, any, any>(postMenuDishText);
    const theme = useTheme()
    const [fileListState, setFileListState] = useState<ImageFile[]>([]);
    const [season, setSeason] = useState(2023);
    const {open, onClose, ...other} = props
    const {
        setValue,
        register,
        handleSubmit,
        watch
    } = useForm();

    const onSubmit = () => {
        const menuDishTextArray: MenuDishText[] = fileListState.map((file, index) => ({
            season: 2023,
        } as MenuDishText));
        mutationPostMenuDishText.mutate(menuDishTextArray)
        if (mutationPostMenuDishText.isSuccess) {
            let formData = new FormData()
            mutationPostMenuDishText.data.map((it, i) => {
                let file = fileListState[i]
                const id = it.id
                formData.append("files", file.file, file.file.name + "." + id)
            })
            mutationPostMenuDishPhoto.mutate(formData)
            onClose()
        }
    };
    useEffect(() => {
        const inputImage: FileList = watch('inputImage') ?? null
        if (inputImage === null || inputImage.length === 0) {
            return
        }
        for (let i = 0; i < inputImage.length; i++) {
            const file = inputImage[i]
            const reader = new FileReader();
            reader.readAsDataURL(file)
            reader.onloadend = () => {
                setFileListState((prev) => [...prev, {file: file, imageSrc: reader.result as string}])
            }
        }
        setValue('inputImage', [])
    }, [watch('inputImage'), fileListState]);
    const handleSeasonChange = (event: any) => {
        setSeason(event.target.value)
    }
    return (
        <Dialog
            component={'form'}
            onSubmit={handleSubmit(onSubmit)}
            open={open}
            TransitionComponent={Transition}
            fullWidth
            maxWidth={'lg'}
            sx={{
                '& .MuiDialog-paper': {
                    backgroundColor: theme.palette.secondary.main,
                    borderRadius: '15px',
                    height: theme.spacing(40),
                }
            }}
            {...other}
        >
            <DialogTitle sx={{padding: 0}}>
                <Stack direction={'row'} justifyContent={'flex-end'}>
                    <IconButton onClick={onClose}>
                        <CancelOutlinedIcon fontSize={'small'}/>
                    </IconButton>
                </Stack>
            </DialogTitle>
            <DialogContent>
                <Grid2 container spacing={1} sx={Grid2ContainerSx}>{
                    fileListState.map((file, index) => {
                        return (
                            <Grid2 key={index} xs={4} md={3} lg={2.4} sx={Grid2ItemSx}>
                                <IconButton sx={CancelRoundedIconSx}
                                            onClick={() => setFileListState(fileListState.filter((_, i) => i !== index))}>
                                    <CancelRoundedIcon fontSize={'small'} htmlColor={'white'}/>
                                </IconButton>
                                <img src={file.imageSrc} alt={""} style={imgStyle}/>
                            </Grid2>
                        )
                    })
                }</Grid2>
            </DialogContent>
            <DialogActions>
                <Stack direction={'row'} spacing={2}>
                    <FormControl>
                        <InputLabel id="season">年份</InputLabel>
                        <Select
                            id="season"
                            value={season}
                            defaultValue={parseInt(props.defaultValue || "")}
                            label="年份"
                            onChange={handleSeasonChange}
                            sx={{fontSize: '10px'}}
                            size={'small'}
                        >
                            {props.searchQuery.split(",").map((it, index) => {
                                if (index === 0) {
                                    return <></>
                                } else {
                                    return <MenuItem value={it}>it</MenuItem>
                                }
                            })
                            }
                        </Select>
                    </FormControl>
                    <Button color={'primary'} component={'label'}>
                        上傳
                        <input
                            hidden
                            accept="image/*"
                            type="file"
                            multiple
                            {...register('inputImage')}
                        />
                    </Button>
                    <Button onClick={() => setFileListState([])}>清除</Button>
                    <Button type={'submit'}>確認</Button>
                </Stack>
            </DialogActions>
        </Dialog>

    )
}
const Grid2ContainerSx: SxProps<Theme> = {}
const Grid2ItemSx: SxProps<Theme> = {
    position: 'relative'
}
const imgStyle = {
    width: '100%',
    height: '100%',
    borderRadius: '15px',
}
const CancelRoundedIconSx: SxProps<Theme> = {
    position: 'absolute',
    right: 0,
    top: 0
}
export default DialogSlide


