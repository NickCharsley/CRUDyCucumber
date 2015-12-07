# CRUDyCucumber

This Project is to help me ensure that Cucumber Jelly (https://github.com/NickCharsley/CucumberJelly) is able to help test Netbeans Projects.

To some extent I'm following Geertjan Wielenga's instructions for **Loosely Coupled Reloadable Capabilities for CRUD Applications** https://dzone.com/articles/loosely-coupled-reloadable-capabilities , although On occasions I think he does jump some simple steps that I think are critical, but hopefully if you compare this to the articles there then you will get some of the pointers I could have done with.

Currently this has:
<ol>
<li>The compleat First Step (Tagged Reloadable), from https://dzone.com/articles/loosely-coupled-reloadable-capabilities.</li>
<li>The compleat Second Step (Tagged Savable), from https://dzone.com/articles/loosely-coupled-saveable-capabilities.</li>
<li>The compleat Third Step (Tagged Creatable), from https://dzone.com/articles/loosely-coupled-creatable-capabilities.</li>
<li>The compleat Fourth Step (Tagged Deletable), from https://dzone.com/articles/loosely-coupled-deletable-capabilities</li>
<li>The essence of the Fifth Step (Tagged OMG), from https://dzone.com/articles/loosely-coupled-data-layers</li>
<li>Some Feature Files and Step Files to test the above.</li>
</ol>

The fifth and final step had some really wicked refactoring, un-exporting various packages, and here the cucumber testing really shone, at each step it was possible to quickly check it wasn't broken.

The final addition is to create the 'text' query and ensure it returned enough data to pass the cucumber tests.

N.B. In the articles between the Second and Third Steps Geertjan switches from the Derby Sample Database due to issues with saving. A later comment appears to indicate how to avoid this issue, so this is Continued with the customer database.



