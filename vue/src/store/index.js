import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
// import MessageService from '../services/MessageService'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    messages: [{
      id: 1,
      band_id: 20,
      name: 'Led Zeppelin',
      date: '12/05/2022',
      content: 'The leaves are falling all around, time I was on my way Thanks to you, Im much obliged for such a pleasant stay.But now its time for me to go, the autumn moon lights my way. For now I smell the rain, and with it pain, and its headed my way. Ah, sometimes I grow so tired. But I know Ive got one thing I got to do.'
    }, {
      id: 2,
      band_id: 10,
      name: 'The Beatles',
      date: '02/12/2022',
      content: 'Baby you can drive my car! Yes Im gonna be a star! Baby you can drive my car! And maybe Ill love you!'
    }
    ]
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    }

  }
})
