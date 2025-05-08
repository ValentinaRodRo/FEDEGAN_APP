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
    { lat: 53.55, lng: 9.99, info: '80 bovinos - Bogotá→Hamburgo' }
];
const outbreakData = [
    { lat: 7.1084, lng: -73.1198, radius: 70000, info: 'Fiebre Aftosa: 42 casos - Santander' },
    { lat: 3.4516, lng: -76.5320, radius: 50000, info: 'Brucelosis: 18 casos - Valle del Cauca' }
];