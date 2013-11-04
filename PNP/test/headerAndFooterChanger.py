import os
import sys

"""
This is a simple script to update the header and footer code of an
entire website with multiple pages after a single change was made. 

Makes use of the following tags:

WITHOUT THESE EXACT TAGS IN THE HTML CODE, THIS SCRIPT WILL NOT WORK!!!

<!-- header --> ... <!-- end of header -->
<!-- footer --> ... <!-- end of footer -->

Note the space in between the dashes and the word in the tag. Place
the respective tag one line before and one line after the code you wish
to replace. 

Functionality: 
    Open a folder and for each HTML file in it:
        1. Load and read the file
        2. Locate the header and footer.
        3. Read headerAndFooterChanges.txt
        4. Replace the appropriate header and footer code
        5. Save and close the file. 
        
    @author James Barney 
    @version 1.0 (6/5/2013)
"""
    
def makeChanges(webPage, newCode):
    with open(webPage) as f: 
        oldLines = f.read().splitlines()   #reads the lines from 'webPage'
        print oldLines

    with open(webPage, 'w') as f:        
        #This chunk of code takes care of the headers on the webpages
        startOldHeader = oldLines.index('<!-- header -->')
        endOldHeader = oldLines.index('<!-- end of header -->')
        startNewHeader = newCode.index('<!-- header -->')
        endNewHeader = newCode.index('<!-- end of header -->')
        
        lengthOldHeader = endOldHeader - startOldHeader
        lengthNewHeader = len(newCode)
        diffOfHeaders = lengthOldHeader - lengthNewHeader
                
        if diffOfHeaders <= 0:
            i = startOldHeader+1
            j = startNewHeader+1
            while i < endOldHeader:     #deletes old code and gives us space to work
                oldLines[i] = '\n'
                i+=1
                
            i = startOldHeader+1
            while i < endOldHeader+1:   #inserts new code
                oldLines.insert(i, newCode[j])
                i+=1
                j+=1
                
            #~ i = 0
            #~ while i < (lengthNewLines + diffOfHeaders):     #cleans up the code
                #~ oldLines.pop()
                #~ i+=1
            #~ oldLines.insert(len(oldLines), '<!-- end of header --!>')
            
                
        if diffOfHeaders > 0: 
            i = startOldHeader+1
            j = startNewHeader+1
            while i < endOldHeader:     #deletes old code and gives us space to work
                oldLines[i] = '\n'
                i+=1
                
            i = startOldHeader+1
            while i < lengthNewHeader:   #inserts new code
                oldLines.insert(i, newCode[j])
                i+=1
                j+=1
                
            #~ i = 0
            #~ while i < (lengthNewLines + diffOfHeaders):     #cleans up the code
                #~ oldLines.pop()
                #~ i+=1
         
         
        #This chunk of code takes care of the footers on the webpages
        print "count of <footer>" + str(oldLines.count('<!-- footer -->'))
        startOldFooter = oldLines.index("<!-- footer -->")
        endOldFooter = oldLines.index('<!-- end of footer -->')
        startNewFooter = newCode.index('<!-- footer -->')
        endNewFooter = newCode.index('<!-- end of footer -->')
        
        lengthOldFooter = endOldFooter - startOldFooter
        lengthNewFooter = len(newCode)
        diffOfFooters = lengthOldFooter - lengthNewFooter
        
        if diffOfFooters <= 0:
            i = startOldFooter+1
            j = startNewFooter+1
            while i < endOldFooter:     #deletes old code and gives us space to work
                oldLines[i] = '\n'
                i+=1
                
            i = startOldFooter+1
            while i < endOldFooter+1:   #inserts new code
                oldLines.insert(i, newCode[j])
                i+=1
                j+=1
                
            #~ i = 0
            #~ while i < (lengthNewLines + diffOfFooters):     #cleans up the code
                #~ oldLines.pop()
                #~ i+=1
            #~ oldLines.insert(len(oldLines), '<!-- end of footer --!>')
            
                
        if diffOfFooters > 0: 
            i = startOldFooter+1
            j = startNewFooter+1
            while i < endOldFooter:     #deletes old code and gives us space to work
                oldLines[i] = '\n'
                i+=1
                
            i = startOldFooter+1
            while i < lengthNewFooter:   #inserts new code
                oldLines.insert(i, newCode[j])
                i+=1
                j+=1
                
            #~ i = 0
            #~ while i < (lengthNewLines + diffOfFooters):     #cleans up the code
                #~ oldLines.pop()
                #~ i+=1
                    
        for line in oldLines:
            f.write(line +'\n')
            
    f.close()       

if __name__ == "__main__":
    with open('headerAndFooterChanges.txt') as g: 
        newLines = g.read().splitlines()                  #opens 'headerAndFooterChanges.txt' as 'g' to make changes using 'newLines'
    directory = set(os.listdir(os.curdir))
    for webPage in directory:
        if webPage.endswith(".html") or webPage.endswith(".htm"):
            print str(webPage)
            makeChanges(webPage, newLines)
            
    g.close()

