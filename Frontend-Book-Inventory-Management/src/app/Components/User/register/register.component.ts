import { Component } from '@angular/core';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Router } from '@angular/router';

import { AuthService } from 'src/app/Configuration/auth.service';

@Component({
  selector: 'app-register',

  templateUrl: './register.component.html',

  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  registrationForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,

    public authService: AuthService,

    public router: Router
  ) {}

  ngOnInit() {
    this.registrationForm = this.formBuilder.group({
      firstname: ['', Validators.required],

      lastname: ['', Validators.required],

      phoneNumber: [
        '',
        [Validators.required, Validators.pattern('^[0-9]{10}$')],
      ],

      username: ['', Validators.required],

      password: ['', Validators.required],
    });

    let status = this.authService.isLoggedIn;

    if (status) {
      const role: string | null = this.authService.getRoleNumber();

      if (role != null) this.authService.navigateToDashboard(role);
      else this.router.navigate(['/home']);
    }
  }

  registerUser() {
    if (this.registrationForm.invalid) {
      console.log('Form not submitted');

      return;
    }

    // Form submission logic goes here

    console.log(this.registrationForm.value);

    this.authService.signUp(this.registrationForm.value).subscribe(
      (res) => {
        if (res.responseMessage) {
          alert(res.responseMessage);

          console.log(res);

          this.registrationForm.reset();

          this.router.navigate(['login']);
        }
      },

      (Error) => {
        console.log(console.error(Error.message));
      }
    );
  }
}
