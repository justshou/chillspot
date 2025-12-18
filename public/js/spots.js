// loadSpots: fetches spots from the server and injects cards into #spotsRow
async function loadSpots() {
  try {
    const res = await fetch('/getAllSpots');
    const spots = await res.json();
    const row = document.getElementById('spotsRow');
    if (!row) return;
    row.innerHTML = '';
    spots.forEach((spot) => {
      const col = document.createElement('div');
      col.className = 'col';
      col.innerHTML = `
        <div class="card shadow-sm">
          <img src="${spot.imgURL || 'chill%20spot%20images/default.jpg'}" alt="${spot.name || ''}" style="object-fit:cover; width:100%; height:225px;" />
          <div class="card-body">
            <p class="card-text text-center">${spot.name || ''}<br><small class="text-muted">${spot.building || ''}${spot.floor ? ' - ' + spot.floor : ''}</small></p>
            <div class="d-flex justify-content-between align-items-center">
              <div class="btn-group">
                <button type="button" class="btn btn-sm btn-outline-secondary btn-view">View</button>
              </div>
              <small class="text-muted">${spot.seats || 0} seats</small>
            </div>
          </div>
        </div>
      `;
      row.appendChild(col);

      const btn = col.querySelector('.btn-view');
      if (btn) {
        btn.addEventListener('click', () => {
          const modalEl = document.getElementById('spotModal');
          if (!modalEl) return;
          const modalTitle = modalEl.querySelector('.modal-title');
          const modalBody = modalEl.querySelector('.modal-body');
          if (modalTitle) modalTitle.textContent = spot.name || 'Spot';
          if (modalBody)
            modalBody.innerHTML = `
              <img src="${spot.imgURL || 'chill%20spot%20images/default.jpg'}" alt="${spot.name || ''}" style="width:100%; height:225px; object-fit:cover;" class="mb-3" />
              <p>${spot.description || ''}</p>
              <p>Building: ${spot.building || ''} ${spot.floor ? ' - ' + spot.floor : ''}</p>
              <p>Seats: ${spot.seats || 0}</p>
            `;
          const bsModal = new bootstrap.Modal(modalEl);
          bsModal.show();
        });
      }
    });
  } catch (err) {
    console.error('Failed to load spots', err);
  }
}

document.addEventListener('DOMContentLoaded', loadSpots);
