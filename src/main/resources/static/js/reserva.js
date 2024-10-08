
$(document).ready(function() {
	loadReservations();

	$('#reservationForm').submit(function(event) {
		event.preventDefault();
		const id = $('#reservationId').val();
		if (id) {
			updateReservation(id);
		} else {
			addReservation();
		}
	});
});

function loadReservations() {
	$.getJSON('/api/reservations', function(data) {
		$('#reservationTableBody').empty();
		data.forEach(reservation => {
			$('#reservationTableBody').append(
				`<tr>
					<td>${reservation.id}</td>
					<td>${reservation.hospede.nome}</td>
					<td>${reservation.quarto.numero}</td>
                    <td>${reservation.dataCheckin}</td>
                    <td>${reservation.horaCheckin}</td>
                    <td>${reservation.dataCheckout}</td>
                    <td>${reservation.horaCheckout}</td>
                    <td>
                    	<button class="btn btn-sm btn-warning" onclick="showEditReservationForm(${reservation.id}, '${reservation.hospede.id}', '${reservation.quarto.id}', '${reservation.dataCheckin}', '${reservation.horaCheckin}', '${reservation.dataCheckout}', '${reservation.horaCheckout}')">Edit</button>
                    	<button class="btn btn-sm btn-danger" onclick="deleteReservation(${reservation.id})">Delete</button>
                    </td>
                 </tr>`
			);
		});
	});
}

function showAddReservationForm() {
	$('#formTitle').text('Add Reserva');
	$('#reservationId').val('');
	$('#reservationGuest').val('');
	$('#reservationRoom').val('');
	$('#reservationDataCheckin').val('');
	$('#reservationHoraCheckin').val('');
	$('#reservationDataCheckout').val('');
	$('#reservationHoraCheckout').val('');
	$('#reservationFormModal').show();
}

function showEditReservationForm(id, nome, numero, dataCheckin, horaCheckin, dataCheckout, horaCheckout) {
	$('#formTitle').text('Edit Reserva');
	$('#reservationId').val(id);
	$('#reservationGuest').val(nome);
	$('#reservationRoom').val(numero);	
	$('#reservationDataCheckin').val(dataCheckin);
	$('#reservationHoraCheckin').val(horaCheckin);
	$('#reservationDataCheckout').val(dataCheckout);
	$('#reservationHoraCheckout').val(horaCheckout);
	$('#reservationFormModal').show();
}

function closeReservationForm() {
	$('#reservationFormModal').hide();
}

function addReservation() {
	const reservation = {
		hospede: { id: $('#reservationGuest').val() },
		quarto: {id: $('#reservationRoom').val() },
		dataCheckin: $('#reservationDataCheckin').val(),
		horaCheckin: $('#reservationHoraCheckin').val(),
		dataCheckout: $('#reservationDataCheckout').val(),
		horaCheckout: $('#reservationHoraCheckout').val(),
	};
	$.ajax({
		url: '/api/reservations',
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(reservation),
		success: function() {
			closeReservationForm();
			loadReservations();
		}
	});
}

function updateReservation(id) {
	const reservation = {
		hospede: {id: $('#reservationGuest').val()},
		quarto: {id: $('#reservationRoom').val()},
		dataCheckin: $('#reservationDataCheckin').val(),
		horaCheckin: $('#reservationHoraCheckin').val(),
		dataCheckout: $('#reservationDataCheckout').val(),
		horaCheckout: $('#reservationHoraCheckout').val(),
		
	};
	$.ajax({
		url: `/api/reservations/${id}`,
		type: 'PUT',
		contentType: 'application/json',
		data: JSON.stringify(reservation),
		success: function() {
			closeReservationForm();
			loadReservations();
		}
	});
}

function deleteReservation(id) {
	if (confirm('Realmente vai deletar?')) {
		$.ajax({
			url: `/api/reservations/${id}`,
			type: 'DELETE',
			success: function() {
				loadReservations();
			}
		});
	}
}
