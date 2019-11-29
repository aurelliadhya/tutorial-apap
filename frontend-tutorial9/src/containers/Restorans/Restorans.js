import React, { Component } from 'react';
import classes from "./Restorans.module.css";
import Restoran from '../../components/Restoran/Restoran';
import axios from '../../axios-restoran';
import Modal from '../../components/UI/Modal/Modal';
import Button from '../../components/UI/Button/Button';

class Restorans extends Component{
    constructor(props) {
        super(props);
        this.state = {
            restorans: [],
            filteredData: [],
            isCreate: false,
            isEdit: false,
            isLoading: true,
            nama: "",
            alamat: "",
            nomorTelepon: "",
            rating: "",
            filtered: ""
        }
    }

    editRestoranHandler(restoran) {
        this.setState({
            isEdit: true,
            idRestoran: restoran.idRestoran,
            nama: restoran.nama,
            nomorTelepon: restoran.nomorTelepon,
            rating: restoran.rating,
            alamat: restoran.alamat
        })
    }

    submitEditRestoranHandler = event => {
        console.log("editing")
        event.preventDefault();
        this.setState({ isLoading: true });
        this.editRestoran();
        this.canceledHandler();
    };

    async editRestoran() {
        const restoranToEdit = {
            idRestoran: this.state.idRestoran,
            nama: this.state.nama,
            alamat: this.state.alamat,
            nomorTelepon: this.state.nomorTelepon,
            rating: this.state.rating
        };

        await axios.put("/restoran/" + this.state.idRestoran, restoranToEdit);
        await this.loadRestorans();
        this.canceledHandler();
    }

    async deleteRestoranHandler(restoranId) {
        await axios.delete(`/restoran/${restoranId}`);
        await this.loadRestorans();
    }

    componentDidMount() {
        this.loadRestorans();
    }

    loadRestorans = async () => {
        const fetchedRestorans = [];
        const response = await axios.get("/restorans");
        for (let key in response.data) {
            fetchedRestorans.push({
                ...response.data[key]
            });
        }
        this.setState({
            restorans: fetchedRestorans,
            filteredData: fetchedRestorans
        });
    }

    addRestoranHandler = () => {
        this.setState({ isCreate: true });
        this.clearForm();
    }

    canceledHandler = () => {
        this.setState({ isCreate: false, isEdit: false });
    }

    changeHandler = event => {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }

    submitAddRestoranHandler = event => {
        event.preventDefault();
        this.setState({ isLoading: true });
        this.addRestoran();
        this.canceledHandler();
    }

    async addRestoran() {
        const restoranToAdd = {
            nama: this.state.nama,
            alamat: this.state.alamat,
            nomorTelepon: this.state.nomorTelepon,
            rating: this.state.rating
        };

        await axios.post("/restoran", restoranToAdd);
        await this.loadRestorans();
    }

    clearForm = () => {
        this.setState({
            isEdit: false,
            idRestoran: null,
            nama: "",
            alamat: "",
            nomorTelepon: "",
            rating: ""
        });
    }

    searchRestoranHandler = event => {
        this.setState({ filtered: event.target.value });
        this.showFilteredData(event.target.value);
    }

    showFilteredData = (value) => {
        let restorans = this.state.restorans;
        restorans = restorans.filter((resto) => {
            return resto.nama.toString().toLowerCase().search(value.toString().toLowerCase()) !== -1;
        });
        this.setState({ filteredData: restorans })
    }

    render() {
        return (
            <React.Fragment>
                <Modal show={this.state.isCreate || this.state.isEdit}
                    modalClosed={this.canceledHandler}>
                    {this.renderForm()}
                </Modal>
                <div className={classes.Title}>All Restorans</div>
                <div className={classes.ButtonLayout}>
                    <button
                        className={classes.AddRestoranButton}
                        onClick={this.addRestoranHandler}
                    >
                        + Add New Restoran
                    </button>
                </div>
                <div>
                    <input placeholder="Search restoran" type="text" value={this.state.filtered} onChange={this.searchRestoranHandler} />
                </div>
                <div className={classes.Restorans}>
                    {this.state.restorans &&
                    this.state.filteredData.map(restoran =>
                        <Restoran 
                            key = {restoran.id}
                            nama = {restoran.nama}
                            alamat = {restoran.alamat}
                            nomorTelepon = {restoran.nomorTelepon}
                            edit={() => this.editRestoranHandler(restoran)}
                            delete={() => this.deleteRestoranHandler(restoran.idRestoran)}
                        />
                    )}
                </div>
            </React.Fragment>
        );
    }

    renderForm() {
        const { isEdit } = this.state;
        return (
            <form>
                <input 
                className={classes.Input}
                name="nama"
                type="text"
                placeholder="Nama"
                value={this.state.nama}
                onChange={this.changeHandler}
                />
                <input 
                className={classes.Input}
                name="nomorTelepon"
                type="number"
                placeholder="Nomor Telepon"
                value={this.state.nomorTelepon}
                onChange={this.changeHandler}
                />
                <textarea 
                className={classes.Input}
                name="alamat"
                type="text"
                placeholder="Alamat"
                value={this.state.alamat}
                onChange={this.changeHandler}
                />
                <input 
                className={classes.Input}
                name="rating"
                type="number"
                placeholder="Rating"
                value={this.state.rating}
                onChange={this.changeHandler}
                />
                <Button btnType="Danger" onClick={this.canceledHandler}>
                    CANCEL
                </Button>
                <Button btnType="Success" onClick={
                    isEdit ? this.submitEditRestoranHandler : this.submitAddRestoranHandler}>
                    SUBMIT
                </Button>
            </form>
        );
    }
}
export default Restorans;
