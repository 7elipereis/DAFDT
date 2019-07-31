function [C1,C2,C3] = pca2originalgenerated(dso,labelso,filenameo, plottitleo, dsg, labelsg, filenameg, plottitleg)

    [coeff, score, latent] = pca(dso);
    
    for i=1:size(coeff,2)
        tempmag(i,1) = abs(min(coeff(:,i)))+abs(max(coeff(:,i)));        
    end
    
    % find Component 1
    C1 = 0;
    for i=1:size(tempmag,1)
        if tempmag(i,1) > C1
            C1 = i;
        end
    end
    
    % find Component 2
    C2 = 0;
    for i=1:size(tempmag,1)
        if tempmag(i,1) > C2 && tempmag(i,1) < tempmag(C1,1)
            C2 = i;
        end
    end
    
    % find Component 3
    C3 = 0;
    for i=1:size(tempmag,1)
        if tempmag(i,1) > C3 && tempmag(i,1) < tempmag(C2,1)
            C3 = i;
        end
    end
    
    %pca ds generated
    [coeffg, scoreg, latentg] = pca(dsg);
    
    set(0,'DefaultFigureVisible','off');
    gscatter(score(:,C1), score(:,C2), labelso, ['r', 'b'], 'o', 5);
    xlabel(strcat('First PC feature index:',num2str(C1)));
    ylabel(strcat('Second PC feature index:',num2str(C2)));    
    %xlim([0 100]);
    %ylim([0 100])
    %xticks(0:10:100)
    %yticks(0:10:100)
    title(plottitleo);
    print(filenameo, '-dpng');
    clf;
    set(0,'DefaultFigureVisible','off');
    gscatter(scoreg(:,C1), scoreg(:,C2), labelsg, ['r', 'b'], 'o', 5);
    xlabel(strcat('First PC feature index:',num2str(C1)));
    ylabel(strcat('Second PC feature index: ',num2str(C2)));    
    %xlim([0 100]);
    %ylim([0 100])
    %xticks(0:10:100)
    %yticks(0:10:100)
    title(plottitleg);
    print(filenameg, '-dpng');
    

end

