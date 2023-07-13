import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPuchaseLogComponent } from './add-puchase-log.component';

describe('AddPuchaseLogComponent', () => {
  let component: AddPuchaseLogComponent;
  let fixture: ComponentFixture<AddPuchaseLogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddPuchaseLogComponent]
    });
    fixture = TestBed.createComponent(AddPuchaseLogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
