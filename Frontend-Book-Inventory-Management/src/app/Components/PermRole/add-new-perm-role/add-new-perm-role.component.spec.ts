import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewPermRoleComponent } from './add-new-perm-role.component';

describe('AddNewPermRoleComponent', () => {
  let component: AddNewPermRoleComponent;
  let fixture: ComponentFixture<AddNewPermRoleComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddNewPermRoleComponent]
    });
    fixture = TestBed.createComponent(AddNewPermRoleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
