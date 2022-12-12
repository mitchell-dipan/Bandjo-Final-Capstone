import axios from 'axios';

const requestConfig = {
    headers: {
        'Content-Type': 'application/json'
    }
};

export default {

    getAllNotifications(time) {
        return axios.get(`/messages?sortBy=${time}`);
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
    addPicture(id, picture) {
        return axios.post(`/create/${id}/picture`, picture);
    },
    addShow(id, show) {
        return axios.post(`/create/${id}/show`, show);
    },
    getAllGenres() {
        return axios.get('/genres');
    },
    addGenreToBand(id, genre) {
        return axios.post(`/create/${id}/genre`, genre);
    },
    follow(bandId) {
        return axios.post(`/follow?bandId=${bandId}`);
    },
    unfollow(bandId) {
        return axios.delete(`/unfollow?bandId=${bandId}`);
    },
    checkFollowStatus(bandId) {
        return axios.get(`/check?bandId=${bandId}`);
    },
    sendMessage(bandId, message) {
        return axios.post(`/bands/${bandId}/sendMessage?messages=${message}`);
    },
    updateBand(id, band) {
        return axios.put(`/bands/${id}/editProfile`, band);
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