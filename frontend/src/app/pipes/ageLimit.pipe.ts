import { Pipe, PipeTransform } from '@angular/core';

@Pipe({name: 'ageLimit'})
export class AgeLimitPipe implements PipeTransform {
    transform(value: string): string {
        let slicedValue = value.replace('AGE_LIMIT_', '');
        if (slicedValue == 'NONE')
            return '-';
        else
            return slicedValue;
    }
}