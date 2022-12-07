import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000"
});

export default {

    getAllNotifications() {
        return http.get(`/`)
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