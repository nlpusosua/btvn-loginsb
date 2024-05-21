const formUserInforEl = document.querySelector('.profile-form');
const usernameEl = document.getElementById('username');


formUserInforEl.addEventListener('submit', async (e) => {
    e.preventDefault();

    const data = {
        id: currentUser.id,
        username: usernameEl.value,
    }
    if (!$('.profile-form').valid()) {
        return;
    }
    try {
        const response = await axios.put("/api/auth/user/update", data);
        const userData = response.data;
        toastr.success('Cập nhật thành công');
    } catch (error) {
        console.log(error);
        toastr.error(error.response.data.message);
    }
})

$('.profile-form').validate({
    rules: {
        username: {
            required: true,
        },
    },
    messages: {
        username: {
            required: "không được để trống username"
        },
    },
    errorElement: 'span',
    errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-group').append(error);
    },
    highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
    },
    unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
    }
});