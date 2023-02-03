import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
    vus: 150,
    duration: '15s'
}

export default function () {
    const res = http.get('http://helidon-nima-client.shukawam.me/client/say');
}