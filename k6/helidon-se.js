import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
    vus: 50,
    duration: '30s'
}

export default function () {
    http.get('http://helidon-se-client.shukawam.me/client/say');
    sleep(1)
}