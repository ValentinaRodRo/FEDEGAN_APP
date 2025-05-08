// Wait until DOM is ready
document.addEventListener('DOMContentLoaded', () => {
    const importSection = document.getElementById('import-export');
    const outbreakSection = document.getElementById('outbreaks');
    const tabImport = document.getElementById('tab-import-export');
    const tabOutbreak = document.getElementById('tab-outbreaks');

    // Initialize both maps and retain references
    const mapImport = createMap('map-import-export', importData, []);
    const mapOutbreak = createMap('map-outbreaks', [], outbreakData);

    // Tab click handlers
    tabImport.addEventListener('click', () => {
        tabImport.classList.add('active');
        tabOutbreak.classList.remove('active');
        importSection.classList.add('active');
        outbreakSection.classList.remove('active');
        mapImport.invalidateSize();
    });

    tabOutbreak.addEventListener('click', () => {
        tabOutbreak.classList.add('active');
        tabImport.classList.remove('active');
        outbreakSection.classList.add('active');
        importSection.classList.remove('active');
        mapOutbreak.invalidateSize();
    });
});

// Factory to create a Leaflet map
function createMap(containerId, markers, circles) {
    const map = L.map(containerId).setView([4.57, -74.29], 6);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; OpenStreetMap contributors'
    }).addTo(map);

    markers.forEach(m => {
        L.marker([m.lat, m.lng]).addTo(map)
            .bindPopup(m.info);
    });

    circles.forEach(o => {
        L.circle([o.lat, o.lng], {
            radius: o.radius,
            color: 'red',
            fillColor: '#f03',
            fillOpacity: 0.3
        }).addTo(map)
            .bindPopup(o.info);
    });

    return map;
}

// Sample data
const importData = [
    { lat: 4.711, lng: -74.072, info: '50 bovinos - Bogotá→Cali' },
    { lat: 6.244, lng: -75.58, info: '30 bovinos - Medellín→Barranquilla' },
    { lat: 25.774, lng: -80.19, info: '100 bovinos - Miami→Bogotá' },
    { lat: 53.55, lng: 9.99, info: '80 bovinos - Bogotá→Hamburgo' },
    { lat: 7.8939, lng: -72.5078, info: '40 bovinos - Cúcuta→Bucaramanga' },
    { lat: -34.6037, lng: -58.3816, info: '70 bovinos - Buenos Aires→Cartagena' },
    { lat: 5.07, lng: -75.52, info: '35 bovinos - Bogotá→Manizales' },
    { lat: 10.4631, lng: -73.2532, info: '25 bovinos - Valledupar→Santa Marta' },
    { lat: 4.7109, lng: -74.0721, info: '55 bovinos - Cali→Pereira' },
    { lat: 29.7604, lng: -95.3698, info: '90 bovinos - Houston→Cartagena' },
    { lat: 40.4168, lng: -3.7038, info: '65 bovinos - Madrid→Bogotá' },
    { lat: 2.935, lng: -75.2891, info: '45 bovinos - Neiva→Ibagué' },
    { lat: 4.142, lng: -73.6266, info: '60 bovinos - Villavicencio→Bogotá' },
    { lat: 10.9685, lng: -74.7813, info: '38 bovinos - Barranquilla→Cartagena' },
    { lat: 2.4469, lng: -76.6147, info: '42 bovinos - Popayán→Pasto' },
    { lat: 8.747, lng: -75.8814, info: '28 bovinos - Montería→Sincelejo' },
    { lat: 11.5449, lng: -72.9072, info: '33 bovinos - Riohacha→Valledupar' },
    { lat: -4.215, lng: -69.9406, info: '22 bovinos - Leticia→Florencia' }
];

const outbreakData = [
    { lat: 7.1084, lng: -73.1198, radius: 70000, info: 'Fiebre Aftosa: 42 casos - Santander' },
    { lat: 3.4516, lng: -76.5320, radius: 50000, info: 'Brucelosis: 18 casos - Valle del Cauca' },
    { lat: 9.337, lng: -73.6537, radius: 45000, info: 'Carbunco: 10 casos - Cesar' },
    { lat: 6.2518, lng: -75.5636, radius: 60000, info: 'Fiebre Aftosa: 25 casos - Antioquia' },
    { lat: 3.9903, lng: -73.9612, radius: 40000, info: 'Fiebre Aftosa: 15 casos - Antioquia' },
];