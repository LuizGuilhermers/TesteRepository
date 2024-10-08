
$(document).ready(function() {
	loadRooms();

	$('#roomForm').submit(function(event) {
		event.preventDefault();
		const id = $('#roomId').val();
		if (id) {
			updateRoom(id);
		} else {
			addRoom();
		}
	});
});

function loadRooms() {
	$.getJSON('/api/rooms', function(data) {
		$('#roomTableBody').empty();
		data.forEach(room => {
			$('#roomTableBody').append(
				`<tr>
					<td>${room.id}</td>
                    <td>${room.tipo}</td>
                    <td>${room.numero}</td>
                    
                    <td>
                    	<button class="btn btn-sm btn-warning" onclick="showEditRoomForm(${room.id}, '${room.tipo}', '${room.numero}')">Edit</button>
                    	<button class="btn btn-sm btn-danger" onclick="deleteRoom(${room.id})">Delete</button>
                    </td>
                 </tr>`
			);
		});
	});
}

function showAddRoomForm() {
	$('#formTitle').text('Add Quarto');
	$('#roomId').val('');
	$('#roomTipo').val('');
	$('#roomNumero').val('');
	$('#roomFormModal').show();
}

function showEditRoomForm(id,tipo,numero) {
	$('#formTitle').text('Edit Quarto');
	$('#roomId').val(id);
	$('#roomTipo').val(tipo);
	$('#roomNumero').val(numero);
	$('#roomFormModal').show();
}

function closeRoomForm() {
	$('#roomFormModal').hide();
}

function addRoom() {
	const room = {
		tipo: $('#roomTipo').val(),
		numero: $('#roomNumero').val(),
		
	};
	$.ajax({
		url: '/api/rooms',
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(room),
		success: function() {
			closeRoomForm();
			loadRooms();
		}
	});
}

function updateRoom(id) {
	const room = {
		tipo: $('#roomTipo').val(),
		numero: $('#roomNumero').val()
		
	};
	$.ajax({
		url: `/api/rooms/${id}`,
		type: 'PUT',
		contentType: 'application/json',
		data: JSON.stringify(room),
		success: function() {
			closeRoomForm();
			loadRooms();
		}
	});
}

function deleteRoom(id) {
	if (confirm('Realmente vai deletar?')) {
		$.ajax({
			url: `/api/rooms/${id}`,
			type: 'DELETE',
			success: function() {
				loadRooms();
			}
		});
	}
}
