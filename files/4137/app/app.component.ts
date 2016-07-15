import {Component} from '@angular/core';

import {HighlightDirective} from './hfff.component';

@Component({
    selector: 'my-app',
    template: `<p [myHighlight]='color'>Highlight me!</p>`,
    directives: [HighlightDirective]
})

export class AppComponent {
}
