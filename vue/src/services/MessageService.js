import axios from 'axios';

export default {

    getAllNotifications() {
        return axios.get('/messages');
    },

    getAllBands() {
        return axios.get('/bands');
    },

    getBandPage(id) {
        return axios.get(`/bands/${id}`);
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