const oldPasswordEl = document.getElementById('oPassword');
const newPasswordEl = document.getElementById('nPassword');
const confirmPasswordEl = document.getElementById('confirmPassword');
const passwordEl = document.querySelector('.profile-form-pass');

passwordEl.addEventListener('submit', async (e) => {
    e.preventDefault();
    if (newPasswordEl.value.trim() !== confirmPasswordEl.value.trim()) {
        toastr.error("2 mật khẩu không trùng nhau");
        return;
    }

    const data = {
        id: currentUser.id,
        oldPassword: oldPasswordEl.value,
        newPassword: newPasswordEl.value,
    }
    if (!$('.profile-form-pass').valid()) {
        return;
    }

    try {
        const response = await axios.put("/api/auth/user/update/password", data);
        const userData = response.data;
    } catch (error) {
        console.log(error);
        toastr.error(error.response.data.message);
    }
})



$('.form-user-password').validate({
    rules: {
        oPassword: {
            required: true,
        },
        nPassword: {
            required: true,
        },
        confirmPassword: {
            required: true,
        }
    },
    messages: {
        oPassword: {
            required: "không được để trống"
        },
        nPassword: {
            required: "không được để trống"
        },
        confirmPassword: {
            required: "không được để trống"
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