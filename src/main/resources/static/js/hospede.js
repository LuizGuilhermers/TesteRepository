
$(document).ready(function() {
	loadGuests();

	$('#guestForm').submit(function(event) {
		event.preventDefault();
		const id = $('#guestId').val();
		if (id) {
			updateGuest(id);
		} else {
			addGuest();
		}
	});
});

function loadGuests() {
	$.getJSON('/api/guests', function(data) {
		$('#guestTableBody').empty();
		data.forEach(guest => {
			$('#guestTableBody').append(
				`<tr>
					<td>${guest.id}</td>
                    <td>${guest.nome}</td>
                    <td>${guest.telefone}</td>
                    <td>${guest.email}</td>
                    <td>${guest.cidade}</td>
                    <td>${guest.estado}</td>
                    <td>${guest.rua}</td>
                    <td>${guest.numero}</td>
                    <td>${guest.cep}</td>
                    <td>${guest.cpf}</td>
                    
                    <td>
                    	<button class="btn btn-sm btn-warning" onclick="showEditGuestForm(${guest.id}, '${guest.nome}', '${guest.telefone}', '${guest.email}', '${guest.cidade}', '${guest.estado}', '${guest.rua}', '${guest.numero}', '${guest.cep}', '${guest.cpf}')">Edit</button>
                    	<button class="btn btn-sm btn-danger" onclick="deleteGuest(${guest.id})">Delete</button>
                    </td>
                 </tr>`
			);
		});
	});
}

function showAddGuestForm() {
	$('#formTitle').text('Add hospede');
	$('#guestId').val('');
	$('#guestNome').val('');
	$('#guestTelefone').val('');
	$('#guestEmail').val('');
	$('#guestCidade').val('');
	$('#guestEstado').val('');
	$('#guestRua').val('');
	$('#guestNumero').val('');
	$('#guestCep').val('');
	$('#guestCpf').val('');
	$('#guestFormModal').show();
}

function showEditGuestForm(id,nome,telefone,email,cidade,estado,rua,numero,cep,cpf) {
	$('#formTitle').text('Edit Hospede');
	$('#guestId').val(id);
	$('#guestNome').val(nome);
	$('#guestTelefone').val(telefone);
	$('#guestEmail').val(email);
	$('#guestCidade').val(cidade);
	$('#guestEstado').val(estado);
	$('#guestRua').val(rua);
	$('#guestNumero').val(numero);
	$('#guestCep').val(cep);
	$('#guestCpf').val(cpf);
	$('#guestFormModal').show();
}

function closeGuestForm() {
	$('#guestFormModal').hide();
}

function addGuest() {
	const guest = {
		nome: $('#guestNome').val(),
		telefone: $('#guestTelefone').val(),
		email: $('#guestEmail').val(),
		cidade: $('#guestCidade').val(),
		estado: $('#guestEstado').val(),
		rua: $('#guestRua').val(),
		numero: $('#guestNumero').val(),
		cep: $('#guestCep').val(),
		cpf: $('#guestCpf').val(),
		
	};
	$.ajax({
		url: '/api/guests',
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(guest),
		success: function() {
			closeGuestForm();
			loadGuests();
		}
	});
}

function updateGuest(id) {
	const guest = {
		nome: $('#guestNome').val(),
		telefone: $('#guestTelefone').val(),
		email: $('#guestEmail').val(),
		cidade: $('#guestCidade').val(),
		estado: $('#guestEstado').val(),
		rua: $('#guestRua').val(),
		numero: $('#guestNumero').val(),
		cep: $('#guestCep').val(),
		cpf: $('#guestCpf').val()
		
	};
	$.ajax({
		url: `/api/guests/${id}`,
		type: 'PUT',
		contentType: 'application/json',
		data: JSON.stringify(guest),
		success: function() {
			closeGuestForm();
			loadGuests();
		}
	});
}

function deleteGuest(id) {
	if (confirm('Realmente vai deletar?')) {
		$.ajax({
			url: `/api/guests/${id}`,
			type: 'DELETE',
			success: function() {
				loadGuests();
			}
		});
	}
}
