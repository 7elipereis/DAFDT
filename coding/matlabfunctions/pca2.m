function [C1,C2,C3] = pca2(ds, labels, filename, plottitle)

    [coeff, score, latent] = pca(ds);
    
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
    
    
    set(0,'DefaultFigureVisible','off');
    gscatter(score(:,C1), score(:,C2), labels, ['r', 'b'], 'o', 5);
    xlabel(strcat('PC ',num2str(C1)));
    ylabel(strcat('PC ',num2str(C2)));
    %xlim([0 100]);
    %ylim([0 100])
    %xticks(0:10:100)
    %yticks(0:10:100)
    title(plottitle);
    print(filename, '-dpng');
    

end

