import axios from 'axios';

const requestConfig = {
    headers: {
        'Content-Type': 'application/json'
    }
};

export default {

    getAllNotifications() {
        return axios.get('/messages');
    },

    getAllBands() {
        return axios.get('/bands');
    },

    getBandPage(id) {
        return axios.get(`/bands/${id}`);
    },
    getGenres(id) {
        return axios.get(`/bands/${id}/genres`)
    },
    getPictures(id) {
        return axios.get(`/bands/${id}/pictures`)
    },
    getShows(id) {
        return axios.get(`/bands/${id}/shows`)
    },
    createBandPage(band) {
        return axios.post('/create', band, requestConfig)
    },
    addPicture(picture) {
        return axios.post('/create/picture', picture);
    }

    // get(id) {
    //     return http.get(`/messages/${id}`);
    // },
    // add(message) {
    //     return http.post(`/message/`, message);
    // },
    // update(message) {
    //     return http.put(`/message/${message.id}`, message);
    // },
    // delete(message) {
    //     return http.delete(`/message/${message}`);
    // }

}